import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Instant;
import java.util.Arrays;
import java.util.ArrayList;

/** 
* Output:
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Inserting to Backend, SHOULD NOT BE CACHED
success
success
First GET from the backend, SHOULD NOT BE CACHED
[test, test]
Second GET from the backend, SHOULD BE CACHED
Getting result from Cache
[test, test]
Inserting into the backend, SHOULD NOT BE CACHED
success
First GET from the backend AFTER insert, SHOULD NOT BE CACHED
[test, test, Hello]
second GET from the backend AFTER insert, SHOULD BE CACHED
Getting result from Cache
[test, test, Hello]
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
**/

public class Main {
	public static void main(String[] args) {
		String action  = "action:insert";
		String body = "body:test";
		
		System.out.println("Inserting to Backend, SHOULD NOT BE CACHED");
		String result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			false
		);
		System.out.println(result);
		
		// This should NOT be cached
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			false
		);
		System.out.println(result);

		
		System.out.println("First GET from the backend, SHOULD NOT BE CACHED");	
		action = "action:get";
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			true
		);
		System.out.println(result);
		
		// This should be cached
		System.out.println("Second GET from the backend, SHOULD BE CACHED");	
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			true
		);
		System.out.println(result);


		System.out.println("Inserting into the backend, SHOULD NOT BE CACHED");	
		action = "action:insert";
		body = "body:Hello";
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			false
		);
		System.out.println(result);
		
		
		System.out.println("First GET from the backend AFTER insert, SHOULD NOT BE CACHED");	
		action = "action:get";
		// Not cached
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			true
		);
		System.out.println(result);
		
		// Cached
		System.out.println("second GET from the backend AFTER insert, SHOULD BE CACHED");	
		result = CoreLibrary.callAPI(
		
			"/",
			action + "|" + body,
			true
		);
		System.out.println(result);
	}
	
	
}

class Backend {
	
	private static ArrayList<String> database = new ArrayList<>();
	
	public static String call(
		String route, 
		String payload
	) {
		final Payload _payload = new Payload(payload);
		if(route.equals("/")) {
			return Backend.home(_payload);
		} else if(route.equals("/todo")) {
			return Backend.todo(_payload);
		} else {
			return "404: Resource doesn't eixst";
		}
	}
	
	public static String home(Payload payload) {
		CoreLibrary.log("Getting from Backend /home and logging payload", "action: " + payload.action, "body: " + payload.body, "key:" + payload.key);
		
		if(payload.action.equals("insert")) {
			database.add(payload.body);
			CoreLibrary.invalidateCacheRoute();
			
			return "success";
		} else if(payload.action.equals("get")) {
			if(payload.key.equals("-1")) { 
				return Arrays.toString(database.toArray());
			} else {
				return database.get(Integer.parseInt(payload.key));
			}
		}
		
		throw new IllegalArgumentException("action must be either insert or get");
	}
	
	public static String todo(Payload payload) {
		CoreLibrary.log("Getting from Backend /todo");
		
		return "todo";
	}
	
	
	private static class Payload {
		private String action;
		private String body;
		private String key;
		
		private static final String ALL_ITEMS = "-1";
		
		public Payload(String payload) {
			final String[] extractedPayload = this.extractPayload(payload);
			this.action = extractedPayload[0];
			this.body = extractedPayload[1];
			this.key = extractedPayload[2];
		}
		
		private String[] extractPayload(String payload) {
			String[] extractedPayload = new String[3];
			final String[] splitPayload = payload.split("\\|");
			
			// id may not exist, so set it to -1 for ALL
			if(splitPayload.length == 2) extractedPayload[2] = ALL_ITEMS;
			for(int i = 0; i < splitPayload.length; ++i) {
				extractedPayload[i] = splitPayload[i].split(":")[1];
			}
			
			return extractedPayload;
		}
		
		public String getAction() {
			return this.action;
		}
		
		public String getBody() {
			return this.body;
		}
	}
}

class CacheEntry<V> {
	private final V value;
	private final long expiry;
	
	public CacheEntry(V value, long timeToLive) {
		this.value = value;
		this.expiry = Instant.now().getEpochSecond() + timeToLive;
	}
	
	public boolean expired() {
		return Instant.now().getEpochSecond() > this.expiry; 
	}
	
	public V getValue() {
		return this.value;
	}
}

class SimpleCache<K,V> {
	private final ConcurrentHashMap<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();
	
	public V get(K key) {
		CacheEntry<V> entry = this.cache.get(key);
		
		if(entry == null) return null;
		if(entry.expired()) {
			this.cache.remove(key);
			return null;
		}
		
		return entry.getValue();
	}
	
	public void put(K key, V value, long timeToLive) {
		this.cache.put(key, new CacheEntry<V>(value, timeToLive));
	}
	
	public void invalidate(K key) {
		this.cache.remove(key);
	}
	
	public void invalidateAll() {
		this.cache.clear();
	}
}

class CoreLibrary {
	private static final SimpleCache<String, String> cache = new SimpleCache<>();
	private static String MODE = "CANCEL";
	
	
	public static String callAPI(
		String route,
		String payload,
		boolean cacheable
	) {
		final String key = route + "::" + payload;
		
		log(
			"Probing Cache",
			"route: " + route,
			"payload: " + payload,
			"key: " + key,
			"cacheable: " + cacheable
		);
		
		if(cacheable) {
			final String cached = cache.get(key);
			logI(
				"In if(cacheable)",
				"cached: " + cached
			);
			
			if(cached != null) {
				System.out.println("Getting result from Cache");
				return cached;
			}
		}
		
		final String result = Backend.call(route, payload);
		if(cacheable) {
			logI(
				"Adding result to cache",
				"key:result:  " + key + ":" + result
			);
			
			cache.put(key, result, 86400);
		}
		
		return result;
	}
	
	public static void invalidateCacheRoute() {
		cache.invalidateAll();
	}
	
	public static long getTime() {
		return Instant.now().getEpochSecond();
	}
	
	public static void log(String... items) {
		if(!MODE.equals("DEBUG")) return;
		
		System.out.println("\n******************************\n[BEGIN DEBUG]\n");
		
		for(String item: items) {
			System.out.println(item);
		}
		
		System.out.println("\n[END DEBUG]\n******************************\n");	
	}
	
	public static void logI(String ... items) {
		if(MODE.equals("CANCEL")) return;
		
		MODE = "DEBUG";
		log(items);
		MODE = "INFO";
	}
}

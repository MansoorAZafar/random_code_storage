#ifndef CACHE_H
#define CACHE_H

#include <unordered_map>
#include <utility>

template<class Key, class Value>
class Cache {
private:

    static constexpr int MAX_LEN = 50;
    std::unordered_map<Key, Value> cache;

public:

    bool contains(Key key) const {
        return this->cache.count(key);
    }

    int add(const std::pair<Key, Value> item) {
        if(this->cache.size() >= Cache::MAX_LEN) {
            this->cache.erase(this->cache.begin());
        }
        this->cache[item.first] = item.second;

        return 0;
    }

    Value get(Key key) {
        return this->cache[key];
    }

};

#endif // CACHE_H

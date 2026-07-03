const ModeTypes = {
    VIEW: 'view',
    EDIT: 'edit'
} as const;

class MinHeap<T> {
    comparator: (a: T, b: T) => boolean;
    private heap: T[] = [];

    constructor(comparator: (a: T, b: T) => boolean) {
        this.comparator = comparator;
    }

    isEmpty(): boolean {
        return this.heap.length === 0;
    }

    peek(): T {
        if (this.isEmpty()) throw new Error("Heap empty");
        return this.heap[0];
    }

    push(value: T) {
        this.heap.push(value);
        this.bubbleUp();
    }

    pop(): T {
        if (this.isEmpty()) throw new Error("Heap empty");

        const top = this.heap[0];
        const last = this.heap.pop()!;

        if (!this.isEmpty()) {
            this.heap[0] = last;
            this.bubbleDown();
        }

        return top;
    }

    private bubbleUp() {
        let index = this.heap.length - 1;

        while (index > 0) {
            const parent = Math.floor((index - 1) / 2);

            if (!this.comparator(this.heap[index], this.heap[parent])) break;

            [this.heap[index], this.heap[parent]] =
                [this.heap[parent], this.heap[index]];

            index = parent;
        }
    }

    private bubbleDown() {
        let index = 0;
        const length = this.heap.length;

        while (true) {
            let left = index * 2 + 1;
            let right = index * 2 + 2;
            let smallest = index;

            if (
                left < length &&
                this.comparator(this.heap[left], this.heap[smallest])
            ) {
                smallest = left;
            }

            if (
                right < length &&
                this.comparator(this.heap[right], this.heap[smallest])
            ) {
                smallest = right;
            }

            if (smallest === index) break;

            [this.heap[index], this.heap[smallest]] =
                [this.heap[smallest], this.heap[index]];

            index = smallest;
        }
    }
}

type HeartBeatShape = {
    user: {
        name: string;
        id: string;
    };
    mode: typeof ModeTypes[keyof typeof ModeTypes];
    sessionId: string;
    resource: string;
    
    // UTC Time
    createdOn: number;
    expiry: number;
};

type HeartBeatResponse = HeartBeatShape[];
type MinHeapResource = {
    expiry: number;
    sessionId: string;
}

const resourceStore = new Map<string, ResourceStore>;
type ResourceStore = {
    sessions: Map<string, HeartBeatShape>,
    expires: MinHeap<MinHeapResource>;
}


function listSessions(resource: string): HeartBeatResponse {
    const results: HeartBeatResponse = [];
    const storedResource = resourceStore.get(resource);
    
    if(!storedResource) return results;
    const storedSessions = storedResource.sessions;
    
    storedSessions.forEach((session) => results.push(session));
    return results;
}

function postSession({
    user,
    mode,
    sessionId,
    resource,
    createdOn,
    expiry
}: HeartBeatShape, maxExpiry: number): HeartBeatResponse {
    const expires 
        = expiry < Date.now() || expiry > maxExpiry ? maxExpiry : expiry;
    
    let storedResource = resourceStore.get(resource);
    if(!storedResource) {
        // If the session doesn't exist, add it
        storedResource = {
            sessions: new Map<string, HeartBeatShape>(),
            expires: new MinHeap<MinHeapResource>((a,b) => a.expiry < b.expiry)
        };
        
        resourceStore.set(resource, storedResource);
    }
    
    const sessions = storedResource.sessions;
    let storedSession = sessions.get(sessionId);
    
    if(!storedSession) {
        // if they don't exist, make it
        storedSession = {
            user,
            mode,
            sessionId,
            resource,
            createdOn,
            expiry: expires
        };
    } else if(storedSession.user.id === user.id) {
        // only the same user can update their session
        storedSession = {
            ...storedSession,
            mode,
            expiry: expires
        };
    } else {
        // throw if they try to edit someone elses session
        throw new Error("Cannot modify someone else's session");
    }
    
    sessions.set(sessionId, storedSession);
    storedResource.expires.push({expiry: expires, sessionId});
    
    return listSessions(resource);   
}

function releaseSession(
    resource: string, 
    sessionId: string, 
    userId: string
) {
    const storedResource = resourceStore.get(resource);
    const sessions = storedResource?.sessions;
    const storedSession = sessions?.get(sessionId);
    
    // if the resource or session don't eixst, it's considered removed
    if(!storedResource || !storedSession || !sessions) return listSessions(resource);
    
    if(storedSession.user.id === userId) sessions.delete(sessionId);
    else throw new Error("Cannot release another person's session");
    
    return listSessions(resource);
}

function purgeExpiredSessions(resource: string) {
    const storedResource = resourceStore.get(resource);
    const sessions = storedResource?.sessions;
    
    if(!storedResource || !sessions) return;
    while(
        !storedResource.expires.isEmpty() 
        && storedResource.expires.peek().expiry < Date.now()
    ) {
        const storedHeapResource = storedResource.expires.pop();
        const authoritiveExpiry 
            = sessions.get(storedHeapResource.sessionId)?.expiry;
        
        if(
            authoritiveExpiry && 
            authoritiveExpiry <= storedHeapResource.expiry
        ) {
            // remove it from the source of truth
            sessions.delete(storedHeapResource.sessionId);
        }
        
    }
    
    if(storedResource.sessions.size === 0) {
        resourceStore.delete(resource);
    }
}

type HeartBeatListQuery = {
    action: 'list';
    data: { resource: string; }
};

type HeartBeatPostQuery = {
    action: 'post';
    data: {
        mode: typeof ModeTypes[keyof typeof ModeTypes];
        sessionId: string;
        resource: string;
        
        // UTC Time
        expiry: number;
    }
};

type HeartBeatReleaseQuery = {
    action: 'release';
    data: { resource: string; sessionId: string; }
};

type HeartBeatQuery = HeartBeatListQuery | HeartBeatPostQuery | HeartBeatReleaseQuery;

const OK = 'OK';
const ERROR = 'ERROR';


function handleSessionsRoute(
    query: HeartBeatQuery,
    user: { id: string; name: string }
) {
    const res = {
        status(code: number) {
            return {
                json(obj: any) {
                    return { status: code, ...obj };
                }
            };
        }
    };

    try {
        if (query.action === "list") {
            purgeExpiredSessions(query.data.resource);

            const data = listSessions(query.data.resource);

            return res.status(200).json({ state: OK, data });
        }

        if (query.action === "post") {
            purgeExpiredSessions(query.data.resource);

            const data = postSession({
                user,
                mode: query.data.mode,
                sessionId: query.data.sessionId,
                resource: query.data.resource,
                createdOn: Date.now(),
                expiry: query.data.expiry
            }, Date.now() + 60000);

            return res.status(200).json({ state: OK, data });
        }

        if (query.action === "release") {
            purgeExpiredSessions(query.data.resource);

            const data = releaseSession(
                query.data.resource,
                query.data.sessionId,
                user.id
            );

            return res.status(200).json({ state: OK, data });
        }

        throw new Error("Invalid action");
    }
    catch (err: any) {
        return res.status(400).json({
            state: ERROR,
            message: err.message
        });
    }
}



function testSessionsRouter() {

    const userA = { id: "1", name: "Alice" };
    const userB = { id: "2", name: "Bob" };

    console.log("\nPOST session A");
    console.log(handleSessionsRoute({
        action: "post",
        data: {
            mode: ModeTypes.EDIT,
            sessionId: "s1",
            resource: "doc1",
            expiry: Date.now() + 5000
        }
    }, userA));

    console.log("\nLIST sessions");
    console.log(handleSessionsRoute({
        action: "list",
        data: { resource: "doc1" }
    }, userA));

    console.log("\nBob tries to modify Alice session (should error)");
    console.log(handleSessionsRoute({
        action: "post",
        data: {
            mode: ModeTypes.VIEW,
            sessionId: "s1",
            resource: "doc1",
            expiry: Date.now() + 5000
        }
    }, userB));

    console.log("\nAlice releases session");
    console.log(handleSessionsRoute({
        action: "release",
        data: { resource: "doc1", sessionId: "s1" }
    }, userA));

    console.log("\nLIST again");
    console.log(handleSessionsRoute({
        action: "list",
        data: { resource: "doc1" }
    }, userA));
}

testSessionsRouter();
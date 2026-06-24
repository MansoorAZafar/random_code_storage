import { useEffect, useState } from "react";

const BasicEffect = () => {
  useEffect(() => {
    console.log('component rendered');   
  })
  
  return ( <></> )
}

const CounterEffect = () => {
  const [count, setCount] = useState(0)
  const handleClick = () => {
    setCount((c) => c + 1)
  }

  useEffect(() => {
    document.title = `Increment: ${count}`
  }, [count])

  return (
    <section>
      <h1>Count: {count}</h1>
      <button onClick={handleClick}>Increment</button>
    </section>  
  );
}

const FetchDataEffect = () => {
  type data = {userId: number, id: number, title: string, body: string}

  const [data, setData] = useState<Array<data>>([])
  const [firstData, setFirstData] = useState<data | null>(null)
  
  const fetchData = async (url: string) => {
    const response = await fetch(url);
    const _data = await response.json();
    
    if(_data && _data.length > 0) {
      setData(_data)
      setFirstData(_data[0])
    }
  }
  
  useEffect(() => {
    fetchData('https://jsonplaceholder.typicode.com/posts');
  }, [])
    
  return (
    <section>
      { data.length > 0 ? <h1>Title: {firstData?.title}</h1> : <h1>Loading...</h1> }
    </section>  
  )
}

const App = () => {
  const styles = {border: '1px red solid', margin: '20px'}
  return (
    <section>
      <div style={styles}>
        <BasicEffect/>
      </div>
      <div style={styles}>
        <CounterEffect/>
      </div>
      <div style={styles}>
        <FetchDataEffect/>
      </div>
    </section>
  );
}

export default App;
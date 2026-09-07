import { useState } from "react";

const DIRECTION = {
  BACKWORDS: -1,
  FORWARDS: 1
};

const Carousel = ({images}) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const moveIndex = (direction) => {
    console.log(`
        direction: ${direction}
        currentIndex + direction: ${currentIndex + direction}
        currentIndex: ${currentIndex}
        images.size: ${images.length}
      `
    );

    const nextIndex = ((currentIndex + direction) + images.length) % images.length;
    setCurrentIndex(nextIndex);
  }

  const createButtonStyle = (direction) => {
    return {
      border: "none",
      width: 50,
      height: 50,
      fontSize: 25,
      position: 'absolute',
      top: "50%",
      [direction == DIRECTION.BACKWORDS ? "right" : "left"]: "87%"
    }
  }

  const styles = {
    leftButton: createButtonStyle(DIRECTION.BACKWORDS),
    rightButton: createButtonStyle(DIRECTION.FORWARDS),

    container: {
      position: 'relative',
      width: 500,
      height: 300
    }
  };

  return (
    <div>
      <h1> index: {currentIndex} </h1>
      
      <div style={styles.container}>
        <img src={images[currentIndex]} width={500} height={300} alt="test"/>
        <button onClick={() => moveIndex(DIRECTION.FORWARDS)} style={styles.rightButton}> 
           &gt;
        </button>
        <button onClick={() => moveIndex(DIRECTION.BACKWORDS)} style={styles.leftButton}>
          &lt;
        </button>
      </div>

    </div>
  );
}

export default function App() {
  
  const images = [
    "https://picsum.photos/500/300",
    "https://raw.githubusercontent.com/bablubambal/All_logo_and_pictures/7c0ac2ceb9f9d24992ec393d11fa7337d2f92466/programming%20languages/bash.svg",
    "https://raw.githubusercontent.com/bablubambal/All_logo_and_pictures/7c0ac2ceb9f9d24992ec393d11fa7337d2f92466/programming%20languages/c%23.svg",
    "https://raw.githubusercontent.com/bablubambal/All_logo_and_pictures/7c0ac2ceb9f9d24992ec393d11fa7337d2f92466/programming%20languages/c++.svg",
    "https://raw.githubusercontent.com/bablubambal/All_logo_and_pictures/7c0ac2ceb9f9d24992ec393d11fa7337d2f92466/programming%20languages/c.svg",
    "https://raw.githubusercontent.com/bablubambal/All_logo_and_pictures/7c0ac2ceb9f9d24992ec393d11fa7337d2f92466/programming%20languages/java.svg"
  ];
  
  return (
    <div>
      <Carousel images={images}/>
    </div>
  );
}

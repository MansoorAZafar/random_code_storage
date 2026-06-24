import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyCQmKs3EYi4vGpSRhP1IbSLzFS81y7T2mI",
  authDomain: "inventory-microservice-c6de8.firebaseapp.com",
  projectId: "inventory-microservice-c6de8",
  storageBucket: "inventory-microservice-c6de8.firebasestorage.app",
  messagingSenderId: "442166043197",
  appId: "1:442166043197:web:ce97914ef0d2266eb18054",
  measurementId: "G-FFRS7CN8Z9"
};

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const app = initializeApp(firebaseConfig);
const provider = new GoogleAuthProvider();

provider.setCustomParameters({
  prompt: "select_account",
});

export const auth = getAuth();
export const popupSignIn = () => signInWithPopup(auth, provider);
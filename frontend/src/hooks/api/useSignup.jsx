"use client"

import { useState } from "react";

export const useSginup = (username, password) => {
  const [isSignup, setIsSignup] = useState(null);

  const handleSginup = async (e) => {
    e.preventDefault();
    console.log(isSignup);

    try {
      const res = await fetch("/api/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });
      const response = await res.json();
      setIsSignup(response.isSignup);
    } catch (err) {
      console.log(err);
    }
  };
  return { isSignup, handleSginup };
};


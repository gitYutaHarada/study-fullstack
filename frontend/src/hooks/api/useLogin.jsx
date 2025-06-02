"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

export const useLogin = (username, password) => {
  const [isLogin, setIsLogin] = useState(false);
  const router = useRouter();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });
      const response = await res.json();
      setIsLogin(response.success);
      if (response.success) {
        router.push("/menu");
      }
    } catch (err) {
      console.log(err);
    }
  };

  return { isLogin, handleLogin };
};

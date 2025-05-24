"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

const LoginForm = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [resData, setResData] = useState(false);
  const router = useRouter();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId, password }),
      });
      const isLogin = await res.json();
      if (isLogin.success) {
        router.push("/menu");
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="userId">userId</label>
          <input
            type="text"
            id="userId"
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">password</label>
          <input
            type="password"
            id="password"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <input type="submit" value="submit"></input>
      </form>
      {resData && <p>a</p>}
    </div>
  );
};

export default LoginForm;

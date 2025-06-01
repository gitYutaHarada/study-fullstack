"use client";

import { useState } from "react";

import { useSginup } from "@/hooks/api/useSignup";

const SignupForm = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const { isSginup, handleSginup } = useSginup(userName, password);

  return (
    <div>
      <p>Sign up Form</p>
      <form onSubmit={handleSginup}>
        <div>
          <label htmlFor="userName">userName</label>
          <input
            type="text"
            id="userName"
            onChange={(e) => setUserName(e.target.value)}
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
    </div>
  );
};

export default SignupForm;

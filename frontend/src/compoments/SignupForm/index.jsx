"use client";

import { useState } from "react";

import { useSginup } from "@/hooks/api/useSignup";

const SignupForm = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const { isSignup, handleSginup } = useSginup(userName, password);

  return (
    <div>
      <p>
        <strong>Sign up Form</strong>
      </p>

      {isSignup && <p>sign up success</p>}
      {isSignup === false && (
        <p>sign up faile (The username is already taken)</p>
      )}
      <form onSubmit={handleSginup}>
        <div>
          <label htmlFor="userName">userName</label>
          <input
            type="text"
            id="userName"
            minLength={3}
            maxLength={20}
            onChange={(e) => setUserName(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">password</label>
          <input
            type="password"
            id="password"
            minLength={3}
            maxLength={20}
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

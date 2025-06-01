"use client"

import { useState } from "react";

export const useSginup = (userName, password) => {
    const [isSignup, setIsSignup] = useState(false);

    const handleSginup = async(e) => {
        e.preventDefault();

        try{
            const res = await fetch("api/signup", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({userName, password}),
            });
            const response = await res.json();
        } catch (err){
            console.log(err);

        }
    }
    return { isSignup, handleSginup};
}


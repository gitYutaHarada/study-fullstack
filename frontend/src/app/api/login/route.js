import { NextResponse } from "next/server";

export const POST = async (reaquest) => {
  try {
    const { userId, password } = await reaquest.json();

    const apiRes = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId, password }),
    });

    const data = await apiRes.json();
    return NextResponse.json(data, { status: apiRes.status });

  } catch (err) {
    console.log(err);
    return NextResponse.json({ error: "サーバーエラー" }, { status: 500 });
  }
};

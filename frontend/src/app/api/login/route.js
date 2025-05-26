import { NextResponse } from "next/server";

export const POST = async (request) => {
  try {
    const { userId, password } = await request.json();

    const apiRes = await fetch("http://localhost:8080/api/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId, password }),
    });

    const data = await apiRes.json();
    console.log("data : " + data);

    if (data) {
      return NextResponse.json({ success: true });
    } else {
      return NextResponse.json({ success: false });
    }
  } catch (err) {
    console.log(err);
    return NextResponse.json({ error: "サーバーエラー" }, { status: 500 });
  }
};

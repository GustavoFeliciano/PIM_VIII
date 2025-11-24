"use client";

import { useState } from "react";
import axios from "axios";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState("");

  async function handleLogin(e: React.FormEvent) {
    e.preventDefault();
    setLoading(true);
    setErro("");

    try {
      const resp = await axios.post("http://localhost:8080/login", {
        email,
        senha
      });

      const token = resp.data.token;

      localStorage.setItem("authToken", token);

      window.location.href = "/upload"; // redireciona após login
    } catch (err: any) {
      setErro("Email ou senha incorretos");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="w-full min-h-screen flex items-center justify-center bg-gray-100">
      <form
        onSubmit={handleLogin}
        className="bg-white shadow-md rounded-lg p-6 w-full max-w-sm"
      >
        <h1 className="text-2xl font-semibold mb-4 text-center">Login</h1>

        {erro && <p className="text-red-500 text-sm mb-2">{erro}</p>}

        <label className="block mb-3">
          <span className="text-gray-700">Email</span>
          <input
            type="email"
            required
            value={email}
            onChange={e => setEmail(e.target.value)}
            className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-blue-500"
          />
        </label>

        <label className="block mb-4">
          <span className="text-gray-700">Senha</span>
          <input
            type="password"
            required
            value={senha}
            onChange={e => setSenha(e.target.value)}
            className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-blue-500"
          />
        </label>

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
        >
          {loading ? "Entrando..." : "Entrar"}
        </button>
      </form>
    </div>
  );
}

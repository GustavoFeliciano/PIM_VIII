"use client";

import axios from "axios";
import { useState } from "react";

export default function UploadPage() {
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [criador, setCriador] = useState("");
  const [video, setVideo] = useState<File | null>(null);
  const [thumb, setThumb] = useState<File | null>(null);

  const enviarConteudo = async () => {
    if (!video || !thumb) {
      alert("Selecione o vídeo e o thumbnail!");
      return;
    }

    try {
      // 1. Envia o arquivo de vídeo
      const formVideo = new FormData();
      formVideo.append("file", video);

      const videoUpload = await axios.post(
        "https://sua-api.com/upload/video",
        formVideo,
        { headers: { "Content-Type": "multipart/form-data" } }
      );

      // 2. Envia o thumbnail
      const formThumb = new FormData();
      formThumb.append("file", thumb);

      const thumbUpload = await axios.post(
        "https://sua-api.com/upload/thumb",
        formThumb,
        { headers: { "Content-Type": "multipart/form-data" } }
      );

      // 3. Salvar no banco com o model Kotlin
      const response = await axios.post("https://localhost/upload", {
        titulo,
        descricao,
        criador: {
          id: Number(criador),
          nome: "Criador automático" // ou selecione de uma listagem vinda da API
        },
        videoPath: videoUpload.data.path,
        thumbnailPath: thumbUpload.data.path
      });

      alert("Upload concluído!");
      console.log("Salvo:", response.data);

    } catch (err) {
      console.error(err);
      alert("Falha ao enviar conteúdo.");
    }
  };

  return (
    <div className="p-10 max-w-xl mx-auto">
      <h1 className="text-3xl font-bold mb-5">Enviar Novo Vídeo</h1>

      <div className="flex flex-col gap-3">

        <input
          type="text"
          placeholder="Título"
          className="border p-2"
          value={titulo}
          onChange={e => setTitulo(e.target.value)}
        />

        <textarea
          placeholder="Descrição"
          className="border p-2"
          value={descricao}
          onChange={e => setDescricao(e.target.value)}
        />

        <input
          type="number"
          placeholder="ID do Criador"
          className="border p-2"
          value={criador}
          onChange={e => setCriador(e.target.value)}
        />

        <label className="font-semibold">Vídeo:</label>
        <input
          type="file"
          className="border p-2"
          accept="video/*"
          onChange={e => setVideo(e.target.files?.[0] ?? null)}
        />

        <label className="font-semibold">Thumbnail:</label>
        <input
          type="file"
          className="border p-2"
          accept="image/*"
          onChange={e => setThumb(e.target.files?.[0] ?? null)}
        />

        <button
          onClick={enviarConteudo}
          className="bg-blue-600 text-white py-2 mt-4 rounded"
        >
          Enviar Vídeo
        </button>
      </div>
    </div>
  );
}

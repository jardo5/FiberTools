import getFiberCount from "./api/FiberTools";
import { useState } from "react";

export default function Home() {
  const [fiberCount, setFiberCount] = useState("");
  const [result, setResult] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    const fiberResult = await getFiberCount(fiberCount);

    setResult(fiberResult);
  };

  return (
    <div className="min-h-screen flex flex-col justify-center items-center bg-gray-100">
      <form onSubmit={handleSubmit} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div className="mb-4">
          <label className="block text-gray-700 font-bold mb-2" htmlFor="fiber-count">
            Fiber Count
          </label>
          <input
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            id="fiber-count"
            type="number"
            placeholder="Enter a number between 1 and 144"
            value={fiberCount}
            onChange={(event) => setFiberCount(event.target.value)}
          />
        </div>
        <div className="flex items-center justify-between">
          <button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
          >
            Generate Colors
          </button>
        </div>
      </form>
      {result && (
        <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
          <p className="text-gray-700 font-bold mb-2">{result}</p>
        </div>
      )}
    </div>
  );
}

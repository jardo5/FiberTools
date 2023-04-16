import axios from "axios";

export default async function getFiberCount(count) {
  try {
    const response = await axios.get(`http://localhost:8080/fiber/${count}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}

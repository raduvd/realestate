import Axios from "axios";

export const CustomAxios = Axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    Accept: "application/json",
  },
});

import axios from 'axios';
import authHeader from "./authentification/auth-header";

const API_URL = "http://localhost:8080";
class SerieDataService
{
    getAll()
    {
        return axios.get(API_URL +"/series",{ headers : authHeader() });
    }

    get(id)
    {
        return axios.get(API_URL +`/series/${id}`,{ headers : authHeader() });
    }

    create(data) {
        return axios.post(API_URL +"/serie", data,{ headers : authHeader() });
    }

    update(id, data) {
        return axios.put(API_URL +`/serie/${id}`, data,{ headers : authHeader() });
    }

    delete(id) {
        return axios.delete(API_URL +`/serie/${id}`,{ headers : authHeader() });
    }

    deleteAll() {
        return axios.delete(API_URL +`/series`,{ headers : authHeader() });
    }

    findByName(name) {
        return axios.get(API_URL +`/serie/${name}`,{ headers : authHeader() });
    }
}

export default new SerieDataService();
import axios from 'axios';
import authHeader from "./authentification/auth-header";

const API_URL = 'http://localhost:8080';
class AuthorDataService
{
    getAll()
    {
        return axios.get(API_URL +`/authors`,{ headers : authHeader() });
    }

    get(id)
    {
        return axios.get(API_URL +`/authors/${id}`,{ headers : authHeader() });
    }

    create(data) {
        return axios.post(API_URL +`/author`, data,{ headers : authHeader() });
    }

    update(id, data) {
        return axios.put(API_URL +`/author/${id}`, data,{ headers : authHeader() });
    }

    delete(id) {
        return axios.delete(API_URL +`/author/${id}`,{ headers : authHeader() });
    }

    deleteAll()
    {
        return axios.delete(API_URL +`/authors`,{ headers : authHeader() });
    }

    findByName(name) {
        return axios.get(API_URL +`/author/${name}`,{ headers : authHeader() });
    }
}

export default new AuthorDataService();
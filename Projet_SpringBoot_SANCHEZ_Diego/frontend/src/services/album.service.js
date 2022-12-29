import axios from 'axios';

import authHeader from "./authentification/auth-header";

const API_URL = 'http://localhost:8080';
class AlbumDataService
{
    getAll()
    {
        return axios.get(API_URL + "/albums",{ headers : authHeader() });
    }

    get(id)
    {
        return axios.get(API_URL +`/albums/${id}`, { headers : authHeader() });
    }

    create(data) {
        return axios.post(API_URL +"/album", data, { headers : authHeader() });
    }

    update(id, data) {
        return axios.put(API_URL +`/album/${id}`, data, { headers : authHeader() });
    }

    delete(id) {
        return axios.delete(API_URL +`/album/${id}`, { headers : authHeader() });
    }

    deleteAll() {
        return axios.delete(API_URL +"/albums", { headers : authHeader() });
    }

    findByName(name) {
        return axios.get(API_URL +`/album/${name}`, { headers : authHeader() });
    }

    /*findBySerieId(id) {
        return http.get(API_URL +`/serie?id=${id}`);
    }*/
}

export default new AlbumDataService();
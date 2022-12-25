import http from "../http-common";

class AlbumDataService
{
    getAll()
    {
        return http.get("/albums");
    }

    get(id)
    {
        return http.get(`/albums/${id}`);
    }

    create(data) {
        return http.post("/album", data);
    }

    update(id, data) {
        return http.put(`/album/${id}`, data);
    }

    delete(id) {
        return http.delete(`/album/${id}`);
    }

    deleteAll() {
        return http.delete(`/albums`);
    }

    findByName(name) {
        return http.get(`/album/${name}`);
    }

    /*findBySerieId(id) {
        return http.get(`/serie?id=${id}`);
    }*/
}

export default new AlbumDataService();
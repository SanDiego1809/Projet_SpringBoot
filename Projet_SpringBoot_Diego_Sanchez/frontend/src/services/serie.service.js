import http from "../http-common";

class SerieDataService
{
    getAll()
    {
        return http.get("/series");
    }

    get(id)
    {
        return http.get(`/series/${id}`);
    }

    create(data) {
        return http.post("/serie", data);
    }

    update(id, data) {
        return http.put(`/serie/${id}`, data);
    }

    delete(id) {
        return http.delete(`/serie/${id}`);
    }

    deleteAll() {
        return http.delete(`/series`);
    }

    findByName(name) {
        return http.get(`/serie/${name}`);
    }

    /*findBySerieId(id) {
        return http.get(`/serie?id=${id}`);
    }*/
}

export default new SerieDataService();
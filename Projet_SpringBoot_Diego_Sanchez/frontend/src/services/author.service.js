import http from "../http-common";

class AuthorDataService
{
    getAll()
    {
        return http.get(`/authors`);
    }

    get(id)
    {
        return http.get(`/authors/${id}`);
    }

    create(data) {
        return http.post(`/author`, data);
    }

    update(id, data) {
        return http.put(`/author/${id}`, data);
    }

    delete(id) {
        return http.delete(`/author/${id}`);
    }

    deleteAll()
    {
        return http.delete(`/authors`);
    }

    findByName(name) {
        return http.get(`/author/${name}`);
    }
}

export default new AuthorDataService();
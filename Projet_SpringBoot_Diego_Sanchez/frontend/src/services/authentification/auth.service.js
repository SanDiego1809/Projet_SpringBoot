import axios from "axios";

const API_URL = "http://localhost:8080";

class AuthService {
    login(username, password) {
        return axios
            .post(API_URL + "/login", {
                password,
                username
            })
            .then(response => {
                console.log(response.headers)
                if (response.headers) {
                    localStorage.setItem("user", JSON.stringify(response.headers));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username, password) {
        return axios.post(API_URL + "/signup", {
            password,
            username
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}

export default new AuthService();
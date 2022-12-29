import React, { Component } from "react";
import AuthorDataService from "../../services/author.service";
import { withRouter } from "../../common/with-router";

class Author extends Component {
    constructor(props) {

        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeCountry = this.onChangeCountry.bind(this);
        this.onChangeDateOfBirth = this.onChangeDateOfBirth.bind(this);
        this.onChangeDateOfDeath = this.onChangeDateOfDeath.bind(this);

        this.getAuthor = this.getAuthor.bind(this);
        this.updateAuthor = this.updateAuthor.bind(this);
        this.deleteAuthor = this.deleteAuthor.bind(this);

        this.state = {
            currentAuthor: {
                id: null,
                name: "",
                firstName: "",
                country: "",
                dateOfBirth: "",
                dateOfDeath: "",
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getAuthor(this.props.router.params.id);
    }

    onChangeName(e) {
        const name = e.target.value;

        this.setState(function(prevState) {
            return {
                currentAuthor: {
                    ...prevState.currentAuthor,
                    name: name
                }
            };
        });
    }

    onChangeFirstName(e) {
        const firstName = e.target.value;

        this.setState(prevState => ({
            currentAuthor: {
                ...prevState.currentAuthor,
                firstName: firstName
            }
        }));
    }

    onChangeCountry(e) {
        const country = e.target.value;

        this.setState(prevState => ({
            currentAuthor: {
                ...prevState.currentAuthor,
                country: country
            }
        }));
    }
    onChangeDateOfBirth(e) {
        const dateOfBirth = e.target.value;

        this.setState(prevState => ({
            currentAuthor: {
                ...prevState.currentAuthor,
                dateOfBirth: dateOfBirth
            }
        }));
    }
    onChangeDateOfDeath(e) {
        const dateOfDeath = e.target.value;

        this.setState(prevState => ({
            currentAuthor: {
                ...prevState.currentAuthor,
                dateOfDeath: dateOfDeath
            }
        }));
    }

    getAuthor(id) {
        AuthorDataService.get(id)
            .then(response => {
                this.setState({
                    currentAuthor: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateAuthor() {
        AuthorDataService.update(
            this.state.currentAuthor.id,
            this.state.currentAuthor
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The author was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteAuthor() {
        AuthorDataService.delete(this.state.currentAuthor.id)
            .then(response => {
                console.log(response.data);
                this.props.router.navigate('/authors');
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentAuthor } = this.state;

        return (
            <div>
                {currentAuthor ? (
                    <div className="edit-form">
                        <h4>Author</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="name"
                                    value={currentAuthor.name}
                                    onChange={this.onChangeName}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="firstName">FirstName</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="firstName"
                                    value={currentAuthor.firstName}
                                    onChange={this.onChangeFirstName}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="country">Country</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="country"
                                    value={currentAuthor.country}
                                    onChange={this.onChangeCountry}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="dateOfBirth">Date Of Birth</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="dateOfBirth"
                                    value={currentAuthor.dateOfBirth}
                                    onChange={this.onChangeDateOfBirth}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="dateOfDeath">Date Of Death</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="dateOfDeath"
                                    value={currentAuthor.dateOfDeath}
                                    onChange={this.onChangeDateOfDeath}
                                />
                            </div>


                        </form>

                        <button
                            className="btn btn-danger"
                            onClick={this.deleteAuthor}
                        >
                            Delete
                        </button>

                        <button
                            type="submit"
                            className="btn btn-warning"
                            onClick={this.updateAuthor}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on an Author...</p>
                    </div>
                )}
            </div>
        );
    }
}

export default withRouter(Author);
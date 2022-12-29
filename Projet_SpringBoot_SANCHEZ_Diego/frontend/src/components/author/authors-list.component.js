import React, { Component } from "react";
import AuthorDataService from "../../services/author.service";
import { Link } from "react-router-dom";

export default class AuthorsList extends Component {

    constructor(props) {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.retrieveAuthors = this.retrieveAuthors.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveAuthor = this.setActiveAuthor.bind(this);
        this.removeAllAuthors = this.removeAllAuthors.bind(this);
        this.searchTitle = this.searchTitle.bind(this);

        this.state = {
            authors: [],
            currentAuthor: null,
            currentIndex: -1,
            searchTitle: ""
        };
    }

    componentDidMount() {
        this.retrieveAuthors();
    }

    onChangeSearchTitle(e) {
        const searchTitle = e.target.value;

        this.setState({
            searchTitle: searchTitle
        });
    }

    retrieveAuthors() {
        AuthorDataService.getAll()
            .then(response => {
                this.setState({
                    authors: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveAuthors();
        this.setState({
            currentAuthor: null,
            currentIndex: -1
        });
    }

    setActiveAuthor(author, index) {
        this.setState({
            currentAuthor: author,
            currentIndex: index
        });
    }

    removeAllAuthors() {
        AuthorDataService.deleteAll()
            .then(response => {
                console.log(response.data);
                this.refreshList();
            })
            .catch(e => {
                console.log(e);
            });
    }

    searchTitle() {
        AuthorDataService.findByName(this.state.searchTitle)
            .then(response => {
                this.setState({
                    authors: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { searchTitle, authors, currentAuthor, currentIndex } = this.state;

        return (
            <div className="list row">
                <div className="col-md-8">
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search by title"
                            value={searchTitle}
                            onChange={this.onChangeSearchTitle}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.searchTitle}
                            >
                                Search
                            </button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <h4>Authors List</h4>

                    <ul className="list-group">
                        {authors &&
                            authors.map((author, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveAuthor(author, index)}
                                    key={index}
                                >
                                    {author.name} {author.firstName}
                                </li>
                            ))}
                    </ul>

                    <button
                        className="m-3 btn btn-sm btn-danger"
                        onClick={this.removeAllAuthors}
                    >
                        Remove All
                    </button>
                </div>
                <div className="col-md-6">
                    {currentAuthor? (
                        <div>
                            <h4>Author</h4>
                            <div>
                                <label>
                                    <strong>Name:</strong>
                                </label>{" "}
                                {currentAuthor.name}
                            </div>
                            <div>
                                <label>
                                    <strong>First Name:</strong>
                                </label>{" "}
                                {currentAuthor.firstName}
                            </div>
                            <div>
                                <label>
                                    <strong>Country:</strong>
                                </label>{" "}
                                {currentAuthor.country}
                            </div>
                            <div>
                                <label>
                                    <strong>Date of Birth:</strong>
                                </label>{" "}
                                {currentAuthor.dateOfBirth}
                            </div>
                            <div>
                                <label>
                                    <strong>Date of Death:</strong>
                                </label>{" "}
                                {currentAuthor.dateOfDeath}
                            </div>


                            <Link
                                to={"/authors/" + currentAuthor.id}
                                className="button danger"
                            >
                                Edit
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please click on an Author...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}
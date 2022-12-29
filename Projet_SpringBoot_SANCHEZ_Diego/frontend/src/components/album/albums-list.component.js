import React, { Component } from "react";
import AlbumDataService from "../../services/album.service";
import { Link } from "react-router-dom";

export default class AlbumsList extends Component {

    constructor(props)
    {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.retrieveAlbums = this.retrieveAlbums.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveAlbum = this.setActiveAlbum.bind(this);
        this.removeAllAlbums = this.removeAllAlbums.bind(this);
        this.searchTitle = this.searchTitle.bind(this);

        this.state = {
            albums: [],
            currentAlbum: null,
            currentIndex: -1,
            searchTitle: ""
        };
    }

    componentDidMount() {
        this.retrieveAlbums();
    }

    onChangeSearchTitle(e) {
        const searchTitle = e.target.value;

        this.setState({
            searchTitle: searchTitle
        });
    }

    retrieveAlbums() {
        AlbumDataService.getAll()
            .then(response => {
                this.setState({
                    albums: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveAlbums();
        this.setState({
            currentAlbum: null,
            currentIndex: -1
        });
    }

    setActiveAlbum(album, index) {
        this.setState({
            currentAlbum: album,
            currentIndex: index
        });
    }

    removeAllAlbums() {
        AlbumDataService.deleteAll()
            .then(response => {
                console.log(response.data);
                this.refreshList();
            })
            .catch(e => {
                console.log(e);
            });
    }

    searchTitle() {
        AlbumDataService.findByName(this.state.searchTitle)
            .then(response => {
                this.setState({
                    albums: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { searchTitle, albums, currentAlbum, currentIndex } = this.state;

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
                    <h4>Albums List</h4>

                    <ul className="list-group">
                        {albums &&
                            albums.map((album, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveAlbum(album, index)}
                                    key={index}
                                >
                                    {album.name} {album.number}
                                </li>
                            ))}
                    </ul>

                    <button
                        className="m-3 btn btn-sm btn-danger"
                        onClick={this.removeAllAlbums}
                    >
                        Remove All
                    </button>
                </div>
                <div className="col-md-6">
                    {currentAlbum? (
                        <div>
                            <h4>Album</h4>
                            <div>
                                <label>
                                    <strong>Name:</strong>
                                </label>{" "}
                                {currentAlbum.name}
                            </div>
                            <div>
                                <label>
                                    <strong>Number:</strong>
                                </label>{" "}
                                {currentAlbum.number}
                            </div>
                            <div>
                                <label>
                                    <strong>Country:</strong>
                                </label>{" "}
                                {currentAlbum.editor}
                            </div>
                            <div>
                                <label>
                                    <strong>Date of Publication:</strong>
                                </label>{" "}
                                {currentAlbum.dateOfPublication}
                            </div>
                            <div>
                                <label>
                                    <strong>Date of Death:</strong>
                                </label>{" "}
                                {currentAlbum.numberOfPages}
                            </div>
                            <div>
                                <label>
                                    <strong>Serie:</strong>
                                </label>{" "}
                                {currentAlbum.serie.name}
                            </div>
                            <div>
                                <label>
                                    <strong>Authors:</strong>
                                </label>{" "}
                                {currentAlbum.authors.map((author) => author.name)}
                                {/*{currentAlbum.authors.name}*/}
                            </div>


                            <Link
                                to={"/albums/" + currentAlbum.id}
                                className="btn btn-warning"
                            >
                                Edit
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please click on an Album...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}
import React, { Component } from "react";
import AlbumDataService from "../../services/album.service";
import { withRouter } from "../../common/with-router";
import AuthorDataService from "../../services/author.service";
import SerieDataService from "../../services/serie.service";
import Multiselect from "react-widgets/Multiselect";

class Album extends Component
{
    constructor(props)
    {

        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeNumber = this.onChangeNumber.bind(this);
        this.onChangeEditor = this.onChangeEditor.bind(this);
        this.onChangeDateOfPublication = this.onChangeDateOfPublication.bind(this);
        this.onChangeNumberOfPages = this.onChangeNumberOfPages.bind(this);
        this.onChangeSerie = this.onChangeSerie.bind(this);
        this.onChangeAuthors = this.onChangeAuthors.bind(this);

        this.getAlbum = this.getAlbum.bind(this);
        this.updateAlbum  = this.updateAlbum.bind(this);
        this.deleteAlbum  = this.deleteAlbum.bind(this);

        this.state = {
            currentAlbum: {
                id: null,
                name: "",
                number: 0,
                editor: "",
                dateOfPublication: "",
                numberOfPages: "",
                authors : [{
                    country: "string",
                    dateOfBirth: "2022-12-14",
                    dateOfDeath: "2022-12-14",
                    firstName: "string",
                    id: 0,
                    name: "string",
                }],
                serie: {
                    genre: "string",
                    id : 0,
                    language: "string",
                    name: "string",
                    numberOfVolumes: 0,
                    origin: "string",
                },

            },
            message: "",
            authorsDB: [],
            seriesDB: [],
            authorsId: [],
        };

        this.getAllAuthors();
        this.getAllSeries();
    }

    componentDidMount() {
        this.getAlbum(this.props.router.params.id);
    }

    onChangeName(e) {
        const name = e.target.value;

        this.setState(function(prevState) {
            return {
                currentAlbum: {
                    ...prevState.currentAlbum,
                    name: name
                }
            };
        });
    }

    onChangeNumber(e) {
        const number = e.target.value;

        this.setState(prevState => ({
            currentAlbum: {
                ...prevState.currentAlbum,
                number: number
            }
        }));
    }

    onChangeEditor(e) {
        const editor = e.target.value;

        this.setState(prevState => ({
            currentAlbum: {
                ...prevState.currentAlbum,
                editor: editor
            }
        }));
    }
    onChangeDateOfPublication(e) {
        const dateOfPublication = e.target.value;

        this.setState(prevState => ({
            currentAlbum: {
                ...prevState.currentAlbum,
                dateOfPublication: dateOfPublication
            }
        }));
    }
    onChangeNumberOfPages(e) {
        const numberOfPages = e.target.value;

        this.setState(prevState => ({
            currentAlbum: {
                ...prevState.currentAlbum,
                numberOfPages: numberOfPages
            }
        }));
    }

    onChangeSerie(e) {
        const serie = e.target.value;
        console.log(serie);

        this.setState(prevState => ({
                currentAlbum: {
                    ...prevState.currentAlbum,
                    serie:{
                        ...prevState.currentAlbum.serie,
                        id : serie,
                    }
                }
            }
        ));

    }

    onChangeAuthors(e) {
        var authorsID = [];
        authorsID = e.target.value;
        //authorsID = parseInt(authorsID)
        console.log(authorsID);
        this.setState(prevState => ({
            currentAlbum:{
                ...prevState.currentAlbum,
                authors:[{
                    id: authorsID,
                    country: "string",
                    dateOfBirth: "2022-12-14",
                    dateOfDeath: "2022-12-14",
                    firstName: "string",
                    name: "string",
                }]
            }
        }));


    }

    getAlbum(id) {
        AlbumDataService.get(id)
            .then(response => {
                this.setState({
                    currentAlbum: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateAlbum() {

        console.log(this.state.authorsDB)
        AlbumDataService.update(
            this.state.currentAlbum.id,
            this.state.currentAlbum
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The album was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteAlbum() {
        AlbumDataService.delete(this.state.currentAlbum.id)
            .then(response => {
                console.log(response.data);
                this.props.router.navigate('/albums');
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllAuthors()
    {
        AuthorDataService.getAll()
            .then(response => {
                this.setState({
                    authorsDB: response.data,
                    authors:response.data.value

                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    getAllSeries()
    {
        SerieDataService.getAll()
            .then(response => {
                this.setState({
                    seriesDB: response.data,
                    serie:response.data.value
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentAlbum } = this.state;
        const options = this.state.authorsDB
        console.log(options)
        return (
            <div>
                {currentAlbum ? (
                    <div className="edit-form">
                        <h4>Album</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="name"
                                    value={currentAlbum.name}
                                    onChange={this.onChangeName}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="number">Number</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="number"
                                    value={currentAlbum.number}
                                    onChange={this.onChangeNumber}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="editor">Editor</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="editor"
                                    value={currentAlbum.editor}
                                    onChange={this.onChangeEditor}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="dateOfPublication">Date Of Publication</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="dateOfPublication"
                                    value={currentAlbum.dateOfPublication}
                                    onChange={this.onChangeDateOfPublication}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="numberOfPages">Number Of Pages</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="numberOfPages"
                                    value={currentAlbum.numberOfPages}
                                    onChange={this.onChangeNumberOfPages}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="serie">Serie</label>
                                <select className="form-control" onChange={this.onChangeSerie} value= {currentAlbum.serie?.id} >
                                    {this.state.seriesDB.map((serie) => (
                                        <option key={serie.id} value={serie.id} >

                                            {serie.name}

                                        </option> ))}

                                </select>
                            </div>

                            <div className="form-group">
                                <label htmlFor="authors">Authors</label>

                                <select className="form-control" onChange={this.onChangeAuthors} value={currentAlbum.authors.id}>
                                    {this.state.authorsDB.map((author) =>
                                        <option key={author.id} value={author.id}>
                                            {author.name}
                                        </option>)}
                                </select>
                            </div>


                        </form>

                        <button
                            className="btn btn-danger"
                            onClick={this.deleteAlbum}
                        >
                            Delete
                        </button>

                        <button
                            type="submit"
                            className="btn btn-warning"
                            onClick={this.updateAlbum}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on an album...</p>
                    </div>
                )}
            </div>
        );
    }
}

export default withRouter(Album);
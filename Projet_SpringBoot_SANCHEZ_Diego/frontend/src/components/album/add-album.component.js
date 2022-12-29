import React, { Component } from "react";
import AlbumDataService from "../../services/album.service";
import AuthorDataService from "../../services/author.service";
import SerieDataService from "../../services/serie.service";
import Select from "react-select";
export default class AddAlbum extends Component
{
    constructor(props)
    {
        super(props);

        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeNumber = this.onChangeNumber.bind(this);
        this.onChangeEditor = this.onChangeEditor.bind(this);
        this.onChangeDateOfPublication= this.onChangeDateOfPublication.bind(this);
        this.onChangeNumberOfPages = this.onChangeNumberOfPages.bind(this);
        this.onChangeSerie = this.onChangeSerie.bind(this);
        this.onChangeAuthors = this.onChangeAuthors.bind(this);
        this.getAllAuthors = this.getAllAuthors.bind(this);

        this.saveAlbum = this.saveAlbum.bind(this);
        this.newAlbum = this.newAlbum.bind(this);

        this.state = {
            id: null,
            name: "",
            number: 0,
            editor: "",
            dateOfPublication: "",
            numberOfPages: 0,
            serie: null,
            authors : [],

            authorsDB: [],
            seriesDB: [],

            submitted: false
        };

        this.getAllAuthors();
        this.getAllSeries();
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeNumber(e) {
        this.setState({
            number: e.target.value
        });
    }

    onChangeEditor(e) {
        this.setState({
            editor: e.target.value
        });
    }

    onChangeDateOfPublication(e) {
        this.setState({
            dateOfPublication: e.target.value
        });
    }

    onChangeNumberOfPages(e) {
        this.setState({
            numberOfPages: e.target.value
        });
    }

    onChangeSerie(e) {
        this.setState({
            serie: e.target.value
        });
        console.log(this.state.serie);
    }

    onChangeAuthors(e) {
        this.setState({
            authors: e.target.value
        });
        console.log(this.state.authors);
    }

    saveAlbum() {
        console.log(this.state.authors);
        console.log(this.state.serie);
        var data = {

            authors : [{
                country: "string",
                dateOfBirth: "2022-12-14",
                dateOfDeath: "2022-12-14",
                firstName: "string",
                id: this.state.authors,
                name: "string",
            }],
            dateOfPublication: this.state.dateOfPublication,
            editor: this.state.editor,
            id: null,
            name: this.state.name,
            number: this.state.number,
            numberOfPages: this.state.numberOfPages,
            serie: {
                genre: "string",
                id : this.state.serie,
                language: "string",
                name: "string",
                numberOfVolumes: 0,
                origin: "string",
            },
        };

        AlbumDataService.create(data)
            .then(response => {
                this.setState({
                    authors : [{
                        country: "string",
                        dateOfBirth: "2022-12-14",
                        dateOfDeath: "2022-12-14",
                        firstName: "string",
                        id: response.data.authors,
                        name: "string",
                    }],
                    dateOfPublication: response.data.dateOfPublication,
                    editor: response.data.editor,
                    //id: response.data.id,
                    name: response.data.name,
                    number: response.data.number,
                    numberOfPages: response.data.numberOfPages,
                    serie: {
                        genre: "string",
                        id : response.data.serie,
                        language: "string",
                        name: "string",
                        numberOfVolumes: 0,
                        origin: "string",
                    },


                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newAlbum() {
        this.setState({
            id: null,
            name: "",
            number: 0,
            editor: "",
            dateOfPublication: 0,
            numberOfPages: 0,
            serieId: null,
            authors : [],

            submitted: false
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
        //const {authorsDB} = this.state;
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={this.newAlbum }>
                            Add
                        </button>
                    </div>
                ) : (
                    <div>

                        <div className="form-group">
                            <label htmlFor="name">Name</label>
                            <input
                                type="text"
                                className="form-control"
                                id="name"
                                required
                                value={this.state.name}
                                onChange={this.onChangeName}
                                name="name"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="number">Number</label>
                            <input
                                type="text"
                                className="form-control"
                                id="number"
                                required
                                value={this.state.number}
                                onChange={this.onChangeNumber}
                                name="number"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="editor">Editor</label>
                            <input
                                type="text"
                                className="form-control"
                                id="editor"
                                required
                                value={this.state.editor}
                                onChange={this.onChangeEditor}
                                name="editor"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="dateOfPublication" >Date Of Publication</label>
                            <input
                                type="text"
                                className="form-control"
                                id="dateOfPublication"
                                placeholder="aaaa-MM-dd"
                                required
                                value={this.state.dateOfPublication}
                                onChange={this.onChangeDateOfPublication}
                                name="dateOfPublication"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="numberOfPages">Number Of Pages</label>
                            <input
                                type="text"
                                className="form-control"
                                id="numberOfPages"
                                required
                                value={this.state.numberOfPages}
                                onChange={this.onChangeNumberOfPages}
                                name="numberOfPages"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="serie">Serie</label>
                            <select className="form-control" name="name" onChange={this.onChangeSerie} value={this.state.serie}>
                                <option value="">Choose a serie</option>
                                {this.state.seriesDB.map((serie) => (
                                    <option key={serie.id} value={serie.id} >

                                        {serie.name}

                                    </option> ))}

                            </select>
                        </div>

                        <div className="form-group">
                            <label htmlFor="authors">Authors</label>
                             <select className="form-control" onChange={this.onChangeAuthors} value={this.state.authors}>
                                 <option value="">Choose authors</option>
                                {this.state.authorsDB.map((author) =>
                                    <option key={author.id} value={author.id}>
                                        {author.name}
                                    </option>)}



                            </select>
                        </div>


                        <button onClick={this.saveAlbum} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
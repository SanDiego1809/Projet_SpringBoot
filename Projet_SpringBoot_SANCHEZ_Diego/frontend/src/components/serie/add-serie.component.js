import React, { Component } from "react";
import SerieDataService from "../../services/serie.service";

export default class AddSerie extends Component
{
    constructor(props)
    {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeGenre = this.onChangeGenre.bind(this);
        this.onChangeNumberOfVolumes = this.onChangeNumberOfVolumes.bind(this);
        this.onChangeOrigin = this.onChangeOrigin.bind(this);
        this.onChangeLanguage = this.onChangeLanguage.bind(this);

        this.saveSerie = this.saveSerie.bind(this);
        this.newSerie = this.newSerie.bind(this);

        this.state = {
            id: null,
            name: "",
            genre: "",
            numberOfVolumes: 0,
            origin: "",
            language: "",

            submitted: false
        };
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeGenre(e) {
        this.setState({
            genre: e.target.value
        });
    }
    onChangeNumberOfVolumes(e) {
        this.setState({
            numberOfVolumes: e.target.value
        });
    }
    onChangeOrigin(e) {
        this.setState({
            origin: e.target.value
        });
    }
    onChangeLanguage(e) {
        this.setState({
            language: e.target.value
        });
    }

    saveSerie() {
        var data = {
            name:  this.state.name,
            genre:  this.state.genre,
            numberOfVolumes:  this.state.numberOfVolumes,
            origin:  this.state.origin,
            language:  this.state.language,
        };

        SerieDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    name:  response.data.name,
                    genre:  response.data.genre,
                    numberOfVolumes:  response.data.numberOfVolumes,
                    origin:  response.data.origin,
                    language:  response.data.language,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newSerie() {
        this.setState({
            id: null,
            name: "",
            genre: "",
            numberOfVolumes: 0,
            origin: "",
            language: "",

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={this.newSerie}>
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
                            <label htmlFor="genre">Genre</label>
                            <input
                                type="text"
                                className="form-control"
                                id="genre"
                                required
                                value={this.state.genre}
                                onChange={this.onChangeGenre}
                                name="genre"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="numberOfVolumes">Number Of Volumes</label>
                            <input
                                type="text"
                                className="form-control"
                                id="numberOfVolumes"
                                required
                                value={this.state.numberOfVolumes}
                                onChange={this.onChangeNumberOfVolumes}
                                name="numberOfVolumes"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="origin">Origin</label>
                            <input
                                type="text"
                                className="form-control"
                                id="origin"
                                required
                                value={this.state.origin}
                                onChange={this.onChangeOrigin}
                                name="origin"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="language">Language</label>
                            <input
                                type="text"
                                className="form-control"
                                id="language"
                                required
                                value={this.state.language}
                                onChange={this.onChangeLanguage}
                                name="language"
                            />
                        </div>

                        <button onClick={this.saveSerie} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
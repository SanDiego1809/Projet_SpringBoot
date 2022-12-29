import React, { Component } from "react";
import SerieDataService from "../../services/serie.service";
import { withRouter } from "../../common/with-router";

class Serie extends Component {
    constructor(props)
    {

        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeGenre = this.onChangeGenre.bind(this);
        this.onChangeNumberOfVolumes = this.onChangeNumberOfVolumes.bind(this);
        this.onChangeOrigin = this.onChangeOrigin.bind(this);
        this.onChangeLanguage = this.onChangeLanguage.bind(this);

        this.getSerie = this.getSerie.bind(this);
        this.updateSerie= this.updateSerie.bind(this);
        this.deleteSerie = this.deleteSerie.bind(this);

        this.state = {
            currentSerie: {
                id: null,
                name: "",
                genre: "",
                numberOfVolumes: 0,
                origin: "",
                language: "",
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getSerie(this.props.router.params.id);
    }

    onChangeName(e) {
        const name = e.target.value;

        this.setState(function(prevState) {
            return {
                currentSerie: {
                    ...prevState.currentSerie,
                    name: name
                }
            };
        });
    }

    onChangeGenre(e) {
        const genre = e.target.value;

        this.setState(prevState => ({
            currentSerie: {
                ...prevState.currentSerie,
                genre: genre
            }
        }));
    }

    onChangeNumberOfVolumes(e) {
        const numberOfVolumes = e.target.value;

        this.setState(prevState => ({
            currentSerie: {
                ...prevState.currentSerie,
                numberOfVolumes: numberOfVolumes
            }
        }));
    }
    onChangeOrigin(e) {
        const origin = e.target.value;

        this.setState(prevState => ({
            currentSerie: {
                ...prevState.currentSerie,
                origin: origin
            }
        }));
    }
    onChangeLanguage(e) {
        const language = e.target.value;

        this.setState(prevState => ({
            currentSerie: {
                ...prevState.currentSerie,
                language: language
            }
        }));
    }

    getSerie(id) {
        SerieDataService.get(id)
            .then(response => {
                this.setState({
                    currentSerie: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateSerie() {
        SerieDataService.update(
            this.state.currentSerie.id,
            this.state.currentSerie
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "The serie was updated successfully!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteSerie() {
        SerieDataService.delete(this.state.currentSerie.id)
            .then(response => {
                console.log(response.data);
                this.props.router.navigate('/series');
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { currentSerie } = this.state;

        return (
            <div>
                {currentSerie ? (
                    <div className="edit-form">
                        <h4>Serie</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="name"
                                    value={currentSerie.name}
                                    onChange={this.onChangeName}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="genre">Genre</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="genre"
                                    value={currentSerie.genre}
                                    onChange={this.onChangeGenre}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="numberOfVolumes">Number of volumes</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="numberOfVolumes"
                                    value={currentSerie.numberOfVolumes}
                                    onChange={this.onChangeNumberOfVolumes}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="origin">Origin</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="origin"
                                    value={currentSerie.origin}
                                    onChange={this.onChangeOrigin}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="language">Language</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="language"
                                    value={currentSerie.language}
                                    onChange={this.onChangeLanguage}
                                />
                            </div>


                        </form>

                        <button
                            className="btn btn-danger"
                            onClick={this.deleteSerie}
                        >
                            Delete
                        </button>

                        <button
                            type="submit"
                            className="btn btn-warning"
                            onClick={this.updateSerie}
                        >
                            Update
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Please click on a Serie...</p>
                    </div>
                )}
            </div>
        );
    }
}

export default withRouter(Serie);
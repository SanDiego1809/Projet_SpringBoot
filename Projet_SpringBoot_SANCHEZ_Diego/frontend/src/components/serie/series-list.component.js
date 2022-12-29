import React, { Component } from "react";
import SerieDataService from "../../services/serie.service";
import { Link } from "react-router-dom";

export default class SeriesList extends Component {

    constructor(props) {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.retrieveSeries = this.retrieveSeries.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveSerie = this.setActiveSerie.bind(this);
        this.removeAllSeries = this.removeAllSeries.bind(this);
        this.searchTitle = this.searchTitle.bind(this);

        this.state = {
            series: [],
            currentSerie: null,
            currentIndex: -1,
            searchTitle: ""
        };
    }

    componentDidMount() {
        this.retrieveSeries();
    }

    onChangeSearchTitle(e) {
        const searchTitle = e.target.value;

        this.setState({
            searchTitle: searchTitle
        });
    }

    retrieveSeries() {
        SerieDataService.getAll()
            .then(response => {
                this.setState({
                    series: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveSeries();
        this.setState({
            currentSerie: null,
            currentIndex: -1
        });
    }

    setActiveSerie(serie, index) {
        this.setState({
            currentSerie: serie,
            currentIndex: index
        });
    }

    removeAllSeries() {
        SerieDataService.deleteAll()
            .then(response => {
                console.log(response.data);
                this.refreshList();
            })
            .catch(e => {
                console.log(e);
            });
    }

    searchTitle() {
        SerieDataService.findByName(this.state.searchTitle)
            .then(response => {
                this.setState({
                    series: response.data
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        const { searchTitle, series, currentSerie, currentIndex } = this.state;

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
                    <h4>Series List</h4>

                    <ul className="list-group">
                        {series &&
                            series.map((serie, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveSerie(serie, index)}
                                    key={index}
                                >
                                    {serie.name} {serie.firstName}
                                </li>
                            ))}
                    </ul>

                    <button
                        className="m-3 btn btn-sm btn-danger"
                        onClick={this.removeAllSeries}
                    >
                        Remove All
                    </button>
                </div>
                <div className="col-md-6">
                    {currentSerie? (
                        <div>
                            <h4>Serie</h4>
                            <div>
                                <label>
                                    <strong>Name:</strong>
                                </label>{" "}
                                {currentSerie.name}
                            </div>
                            <div>
                                <label>
                                    <strong>Genre:</strong>
                                </label>{" "}
                                {currentSerie.genre}
                            </div>
                            <div>
                                <label>
                                    <strong>Number Of Volumes:</strong>
                                </label>{" "}
                                {currentSerie.numberOfVolumes}
                            </div>
                            <div>
                                <label>
                                    <strong>Origin:</strong>
                                </label>{" "}
                                {currentSerie.origin}
                            </div>
                            <div>
                                <label>
                                    <strong>Language:</strong>
                                </label>{" "}
                                {currentSerie.language}
                            </div>


                            <Link
                                to={"/series/" + currentSerie.id}
                                className="btn btn-warning"
                            >
                                Edit
                            </Link>
                        </div>
                    ) : (
                        <div>
                            <br />
                            <p>Please click on a Serie...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}
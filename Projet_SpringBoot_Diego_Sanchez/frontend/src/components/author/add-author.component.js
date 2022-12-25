import React, { Component } from "react";
import AuthorDataService from "../../services/author.service";

export default class AddAuthor extends Component
{
    constructor(props)
    {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeCountry = this.onChangeCountry.bind(this);
        this.onChangeDateOfBirth = this.onChangeDateOfBirth.bind(this);
        this.onChangeDateOfDeath = this.onChangeDateOfDeath.bind(this);
        this.saveAuthor = this.saveAuthor.bind(this);
        this.newAuthor = this.newAuthor.bind(this);

        this.state = {
            id: null,
            name: "",
            firstName: "",
            country: "",
            dateOfBirth: "",
            dateOfDeath: "",

            submitted: false
        };
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeFirstName(e) {
        this.setState({
            firstName: e.target.value
        });
    }
    onChangeCountry(e) {
        this.setState({
            country: e.target.value
        });
    }
    onChangeDateOfBirth(e) {
        this.setState({
            dateOfBirth: e.target.value
        });
    }
    onChangeDateOfDeath(e) {
        this.setState({
            dateOfDeath: e.target.value
        });
    }

    saveAuthor() {
        var data = {
            name: this.state.name,
            firstName: this.state.firstName,
            country: this.state.country,
            dateOfBirth: this.state.dateOfBirth,
            dateOfDeath: this.state.dateOfDeath,
        };

        AuthorDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    name: response.data.name,
                    firstName: response.data.firstName,
                    country:response.data.country,
                    dateOfBirth: response.data.dateOfBirth,
                    dateOfDeath: response.data.dateOfDeath,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newAuthor() {
        this.setState({
            id: null,
            name: "",
            firstName: "",
            country: "",
            dateOfBirth: "",
            dateOfDeath: "",

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>You submitted successfully!</h4>
                        <button className="btn btn-success" onClick={this.newAuthor}>
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
                            <label htmlFor="firstName">FirstName</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                required
                                value={this.state.firstName}
                                onChange={this.onChangeFirstName}
                                name="firstName"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="country">Country</label>
                            <input
                                type="text"
                                className="form-control"
                                id="country"
                                required
                                value={this.state.country}
                                onChange={this.onChangeCountry}
                                name="country"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="dateOfBirth" >DateOfBirth</label>
                            <input
                                type="text"
                                className="form-control"
                                id="dateOfBirth"
                                placeholder="aaaa-MM-dd"
                                required
                                value={this.state.dateOfBirth}
                                onChange={this.onChangeDateOfBirth}
                                name="dateOfBirth"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="dateOfDeath" placeholder="aaaa-MM-dd">DateOfDeath</label>
                            <input
                                type="text"
                                className="form-control"
                                id="dateOfDeath"
                                placeholder="aaaa-MM-dd"
                                required
                                value={this.state.dateOfDeath}
                                onChange={this.onChangeDateOfDeath}
                                name="dateOfDeath"
                            />
                        </div>

                        <button onClick={this.saveAuthor} className="btn btn-success">
                            Submit
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
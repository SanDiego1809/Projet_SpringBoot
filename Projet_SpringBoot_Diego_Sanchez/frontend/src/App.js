import './App.css';
import {Component} from "react";
import "bootstrap/dist/css/bootstrap.min.css"

import { Route, Link, Routes} from "react-router-dom";

import AddAlbum from "./components/album/add-album.component";
import AlbumsList from "./components/album/albums-list.component";
import Album from "./components/album/album.component";

import AuthorsList from "./components/author/authors-list.component";
import AddAuthor from "./components/author/add-author.component";
import Author from "./components/author/author.component";

import SeriesList from "./components/serie/series-list.component";
import AddSerie from "./components/serie/add-serie.component";
import Serie from "./components/serie/serie.component";

import AuthService from "./services/authentification/auth.service";

import Login from "./components/authentification/login.component";
import Register from "./components/authentification/register.component";
import Home from "./components/home/home.component";

import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";


class App extends Component
{
    constructor(props)
    {
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            currentUser: undefined,
        };
    }

    componentDidMount()
    {
        const user = AuthService.getCurrentUser();

        if (user) {
            this.setState({
                currentUser: user,
                /*showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
                showAdminBoard: user.roles.includes("ROLE_ADMIN"),*/
            });
        }
    }

    logOut()
    {
        AuthService.logout();
        this.setState({
            currentUser: undefined,
        });
    }
    render() {
        const {currentUser, showModeratorBoard, showAdminBoard} = this.state;
        return (
            <div className="App">
                <header>
                    <Navbar bg="light" expand="lg">
                        <Container>
                            <Navbar.Brand href={"#"}> SpringBoot</Navbar.Brand>
                            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                            <Navbar.Collapse id="basic-navbar-nav">
                                {currentUser && (
                                <Nav className="me-auto">
                                    <NavDropdown title="Album" id="basic-nav-dropdown">
                                        <NavDropdown.Item href={"/albums"}>
                                            All
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href={"/album/add"}>
                                            Add
                                        </NavDropdown.Item>
                                    </NavDropdown>

                                    <NavDropdown title="Author" id="basic-nav-dropdown">
                                        <NavDropdown.Item href={"/authors"}>
                                            All
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href={"/author/add"}>
                                            Add
                                        </NavDropdown.Item>
                                    </NavDropdown>

                                    <NavDropdown title="Serie" id="basic-nav-dropdown">
                                        <NavDropdown.Item href={"/series"}>
                                            All
                                        </NavDropdown.Item>
                                        <NavDropdown.Item href={"/serie/add"}>
                                            Add
                                        </NavDropdown.Item>
                                    </NavDropdown>
                                </Nav>
                                )}
                                {currentUser ? (
                                    <Nav>
                                        <Nav.Link href="/login" id="logout" onClick={this.logOut}>Logout</Nav.Link>
                                    </Nav>
                                ) : (
                                    <Nav>
                                        <Nav.Link href="/login" id="login">Login</Nav.Link>
                                        <Nav.Link href="/register" id="signup">Sign Up</Nav.Link>
                                    </Nav>
                                )}
                                </Navbar.Collapse>


                        </Container>
                    </Navbar>
                </header>
                <div className="container mt-3">
                    <Routes>

                        <Route path="/" element={<Login/>}/>
                        <Route path="/home" element={<Home/>}/>

                        <Route path="/login" element={<Login/>}/>
                        <Route path="/register" element={<Register/>}/>

                        <Route path="/albums" element={<AlbumsList/>}/>
                        <Route path="/album/add" element={<AddAlbum/>}/>
                        <Route path="/albums/:id" element={<Album/>}/>

                        <Route path="/authors" element={<AuthorsList/>}/>
                        <Route path="/author/add" element={<AddAuthor/>}/>
                        <Route path="/authors/:id" element={<Author/>}/>

                        <Route path="/series" element={<SeriesList/>}/>
                        <Route path="/serie/add" element={<AddSerie/>}/>
                        <Route path="/series/:id" element={<Serie/>}/>
                    </Routes>
                </div>
            </div>
        );
    }
}

export default App;
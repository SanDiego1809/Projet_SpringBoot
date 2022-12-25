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

import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";


function App()
{
    return (
        <div className="App">
            <header>
                <Navbar bg="light" expand="lg">
                    <Container>
                        <Navbar.Brand href={"/"}> SpringBoot</Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
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

                        </Navbar.Collapse>
                    </Container>
                </Navbar>
            </header>
            <div className="container mt-3">
                <Routes>

                    <Route path="/albums" element={<AlbumsList/>} />
                    <Route path="/album/add" element={<AddAlbum/>} />
                    <Route path="/albums/:id" element={<Album/>} />

                    <Route path="/authors" element={<AuthorsList/>} />
                    <Route path="/author/add" element={<AddAuthor/>} />
                    <Route path="/authors/:id" element={<Author/>} />

                    <Route path="/series" element={<SeriesList/>} />
                    <Route path="/serie/add" element={<AddSerie/>} />
                    <Route path="/series/:id" element={<Serie/>} />
                </Routes>
            </div>
        </div>



         /* <div>
            <nav className="navbar navbar-expand navbar-dark bg-dark">
              <Link to={"/"} className="navbar-brand">
                SpringBoot
              </Link>

              <div className="navbar-nav mr-auto">

                <li className="nav-item">
                  <Link to={"/albums"} className="nav-link">
                    Albums
                  </Link>
                </li>
                <li className="nav-item">
                  <Link to={"/add"} className="nav-link">
                    Add
                  </Link>
                </li>
              </div>
            </nav>

            <div className="container mt-3">
                <Routes>
                    <Route path="/" element={<AddAuthor/>} />
                    <Route path="/albums" element={<AlbumsList/>} />
                    <Route path="/add" element={<AddAlbum/>} />
                    <Route path="/series/:id/albums" element={<Album/>} />
                </Routes>
            </div>
          </div>*/
    );
}

export default App;
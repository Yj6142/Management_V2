import {Container, Nav, Navbar} from "react-bootstrap";

function NavigationBar () {
    return(
    <Navbar bg="dark" data-bs-theme="dark">
        <Container>
            <Navbar.Brand href="/home"></Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/quotation">Quotation</Nav.Link>
                <Nav.Link href="/order">Order</Nav.Link>
                <Nav.Link href="/company">Company</Nav.Link>
            </Nav>
        </Container>
    </Navbar>
    );
}

export default NavigationBar;
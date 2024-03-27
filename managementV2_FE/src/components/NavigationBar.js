import {Container, Nav, Navbar} from "react-bootstrap";

function NavigationBar () {
    return(
    <Navbar bg="dark" data-bs-theme="dark">
        <Container>
            <Navbar.Brand href="/home"></Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/company">견적/주문</Nav.Link>
                <Nav.Link href="/orders">이윤</Nav.Link>
            </Nav>
        </Container>
    </Navbar>
    );
}

export default NavigationBar;
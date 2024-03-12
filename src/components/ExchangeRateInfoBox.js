import {Card, Col, Row} from "react-bootstrap";
import "./ExchangeRateInfoBox.css";

function ExchangeRateInfoBox({data}) {

    const currencyInfo = {
        USD : {name : "미국", flagImage : "/usa.svg"},
        CNY : {name : "중국", flagImage : "/china.png"},
        EUR : {name : "유럽", flagImage: "/europe.png"}
    }

    return (
        <div className="rateInfoBox">
            <Row className="g-4">
                {
                    data.map((item, index) => {
                        const {name, flagImage} = currencyInfo[item.cur_unit]

                        return (
                            <Col key={index}>
                            <Card>
                                <Card.Header><img src={`/images/flag${flagImage}`}
                                                  style={{width: '50px', height: '30px'}} alt={name}/>{name}
                                </Card.Header>
                                <Card.Body>
                                    <Card.Title>실시간 환율</Card.Title>
                                    <Card.Text>{item.deal_bas_r}</Card.Text>
                                    <Card.Title>송금 할 때</Card.Title>
                                    <Card.Text>{item.tts}</Card.Text>
                                    <Card.Title>송금 받을 때</Card.Title>
                                    <Card.Text>{item.ttb}</Card.Text>

                                </Card.Body>
                            </Card>
                            </Col>
                        );
                    })
                }
            </Row>
        </div>
    );
}

export default ExchangeRateInfoBox;
import axios from "axios";

const getExchangeRateInfo = async () => {
    const response = await axios.get("http://localhost:8080/todayRate");
    return response.data.filter(item => item.cur_unit === 'USD' || item.cur_unit === 'CNH' || item.cur_unit === 'EUR');
}

export {getExchangeRateInfo};
import axios from "axios";

const getProfitList = async () => {
    try{
        const response = await axios.get("http://localhost:8080/orders/todayProfit");
        return response.data;
    } catch (e) {
        console.log("데이터를 불러오는데 실패하였습니다");
    }
}

const getDailyProfit = async ({date}) => {
    try{
        const response = await axios.post("http://localhost:8080/orders/todayProfit",{
            orderDate: date,
        })
        return response.data;
    } catch (e) {
        console.log("데이터를 찾는데 실패했습니다.");
    }
}

export {getProfitList, getDailyProfit};
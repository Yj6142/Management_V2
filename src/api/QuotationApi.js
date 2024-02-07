import axios from "axios";

const searchItem = async (data) => {
    try{
        const response = await axios.post("http://localhost:8080/search",data)
        return response.data;
    } catch (e) {
        console.log("데이터를 불러오지 못했습니다.");
    }
}

const addQuotation = async (data) => {
    try{
        const response = await axios.post(`http://localhost:8080/quotation/${data.companyName}`, data.quotationList)
        return response.data;
    } catch (e) {
        console.log("데이터를 저장하는데 실패하였습니다.");
    }
}

export {searchItem, addQuotation};
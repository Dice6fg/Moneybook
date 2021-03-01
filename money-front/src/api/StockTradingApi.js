import axios from 'axios';

const config = {
    withCredentials: true,
}

const Url = 'http://localhost:8080/api/v1/stock_trading';

// get 
export async function getStockTrading(year, month) {
   
    const getUrl = Url + '/' + year + '/' + month;

    try {
        const response = await axios.get(getUrl, config);
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// create Api

export async function createStockTrading(tradingData) {

    try {
        const response = await axios.post(Url,{
            "ticker": tradingData.ticker,
            "price": tradingData.price,
            "stockquantity": tradingData.stockquantity,
            "year": tradingData.year,
            "month": tradingData.month,
            "day": tradingData.day,
        }, config);
       
        console.log(response)
        return response.data;

    } catch (error) {
        return error;
    }
}

// update Api

export async function updateStockTrading(id, tradingData) {
    const getUrl = Url + '/' + id;
    console.log(tradingData);
    try {
        const response = await axios.put(getUrl, {
            "price": tradingData.price,
            "stockquantity": tradingData.stockquantity,
            "year": tradingData.year,
            "month": tradingData.month,
            "day": tradingData.day,
        }, config)
        return response.data;

    } catch (error) {
        return error;
    }
}

// delete Api 

export async function deleteStockTrading(id) {
    const getUrl = Url + '/' + id;
    try {
        const response = await axios.delete(getUrl)
        return response.data;
    } catch (error) {
        return error;
    }
}

// get stock by ticker

export async function getStockByTicker(ticker) {
    const getUrl = Url + '/' + ticker;
    try {
        const response = await axios.get(getUrl)
        return response.data;
    } catch (error) {
        return error;
    }
}


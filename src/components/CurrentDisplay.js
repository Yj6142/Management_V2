function CurrentDisplay ({price, currency}) {

    let formatter;
    if(currency === 'USD') {
        formatter = Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: currency,
        })
    } else if(currency === 'KRW') {
        formatter = Intl.NumberFormat('ko-KR', {
            style : 'currency',
            currency : currency,
        })
    } else if(currency === 'JPY') {
        formatter = Intl.NumberFormat('ja-JP',{
            style : 'currency',
            currency : currency,
        })
    } else if(currency === 'CNY'){
        formatter = Intl.NumberFormat('zh-CN',{
            style : 'currency',
            currency : currency,
        })
    }

    return(
        <div>
            <span>{formatter.format(price)}</span>
        </div>
    )
}

export default CurrentDisplay;
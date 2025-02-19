<?php

# Start the application by localhost, unfortunately i can't make it work directly from vscode.

    $ch = curl_init('https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml?5105e8233f9433cf70ac379d6ccc5775');
    curl_setopt($ch, CURLOPT_FOLLOWLOCATION,1);
    curl_setopt($ch, CURLOPT_HEADER,0);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT,0);
    $xml = simplexml_load_string(curl_exec($ch),"SimpleXMLElement", LIBXML_NOCDATA);

    curl_close($ch);

    $array = json_decode(json_encode($xml), true);

    $currencyArray = array();

    $date = $array["Cube"]["Cube"]["@attributes"]["time"];

    for($i = 0; $i < count($array["Cube"]["Cube"]["Cube"]); $i++){
        $currency = $currencyArray[$i]["currency"] = $array["Cube"]["Cube"]["Cube"][$i]["@attributes"]["currency"];
        $rate = $array["Cube"]["Cube"]["Cube"][$i]["@attributes"]["rate"];
        $currencyArray[$i]["currency"] = $currency;
        $currencyArray[$i]["rate"] = $rate;
    }

    $fileName = "usd_currency_rates_$date.csv";
    $headers = ['Currency Code', 'Rate'];    

    $fp = fopen($fileName , 'w');

    fputcsv($fp, $headers);

    foreach ($currencyArray as $fields) {
        fputcsv($fp, $fields);
    }

    fclose($fp);
    
?>
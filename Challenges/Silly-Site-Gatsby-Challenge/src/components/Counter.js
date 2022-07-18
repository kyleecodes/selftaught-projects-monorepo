import React, { useEffect, useState } from "react";
import graphic from "../images/piratekitty.png"

const Counter = (props) => {
  const calculateTimeLeft = () => {
    let year = new Date().getFullYear();
    const difference = +new Date(`${year}-9-19`) - +new Date();
    let timeLeft = {};

    if (difference > 0) {
      timeLeft = {
        days: Math.floor(difference / (1000 * 60 * 60 * 24)),
        hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
        minutes: Math.floor((difference / 1000 / 60) % 60),
        seconds: Math.floor((difference / 1000) % 60),
      };
    }

    return timeLeft;
  };

  const [timeLeft, setTimeLeft] = useState(calculateTimeLeft());

  useEffect(() => {
    setTimeout(() => {
      setTimeLeft(calculateTimeLeft());
    }, 1000);
  });

  const timerComponents = [];

  Object.keys(timeLeft).forEach((interval) => {
    if (!timeLeft[interval]) {
      return;
    }

    timerComponents.push(
      <span>
        {timeLeft[interval]} {interval}{" "}
      </span>
    );
  });
  return (
    <div>
      <img src={graphic} alt="pirate-kitty-icon"/>
      <h3>"Meow like a Pirate Day" 2021 Counter!</h3>
      {timerComponents.length ? timerComponents : <span>Time's up!</span>}
      <h4>until September 19th, 2021</h4>
      <i>
      For more information about Meow Like a Pirate Day, <a href={"https://www.google.com/search?rlz=1C1CHBF_enUS862US862&sxsrf=ALeKk02BDzrQQwbcLuFeZFLNfsw2j_N_qw%3A1609392944521&ei=MGPtX9-hH5_P0PEPxMuF-As&q=meow+like+a+pirate+day&oq=meow+like+a+pirate+day&gs_lcp=CgZwc3ktYWIQAzIHCCMQyQMQJzoHCCMQ6gIQJzoECCMQJzoFCAAQkQI6CAguELEDEIMBOgsILhCxAxDHARCjAjoICAAQsQMQgwE6BQgAELEDOgQIABBDOgcIABCxAxBDOgQILhBDOgoILhDHARCjAhBDOgoIABCxAxDJAxBDOgcILhCxAxBDOhAILhCxAxDHARCvARAUEIcCOgUILhCxAzoKCC4QsQMQyQMQQzoNCC4QsQMQxwEQrwEQQzoLCC4QsQMQxwEQrwE6AggAOgUIABDJAzoHCAAQFBCHAjoICC4QxwEQrwE6AgguOgcILhAUEIcCOgYIABAWEB5QxAdYvhZgvhdoAXACeACAAd0BiAHQHJIBBjAuMTcuNJgBAKABAaoBB2d3cy13aXqwAQrAAQE&sclient=psy-ab&ved=0ahUKEwjf-Yv2v_ftAhWfJzQIHcRlAb8Q4dUDCA4&uact=5"}> look it up!</a> ️
      </i>
      🏴‍☠️
    </div>
  );
}

export default Counter;
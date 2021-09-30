import React from "react";

export const MatchSmallCard = ({match}) =>
{
    return (
        <div className="MatchSmallCard ">

            <p>{match.team1} vrs {match.team2}</p>

        </div>
    );
} 
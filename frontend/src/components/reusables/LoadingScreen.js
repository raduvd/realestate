//TODO this should be a sheet over the screen that will disable everything under and appear after a prompt usually

import React from "react";

class LoadingScreen extends React.Component {
    render() {
        return (
            <div className="ui segment">
                <p></p>
                <div className="ui active dimmer">
                    <div className="ui loader"></div>
                </div>
            </div>
        );
    }
}
export default LoadingScreen;
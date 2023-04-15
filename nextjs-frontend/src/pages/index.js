import React, { Component } from 'react';

import FiberToolsService from '../server/FiberTools';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      fiberCount: '',
      fiberColor: ''
    }
    this.generateFiberColor = this.generateFiberColor.bind(this);
    this.handleFiberCountChange = this.handleFiberCountChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  generateFiberColor() {
    FiberToolsService.generateFiberColor(this.state.fiberCount)
      .then(response => {
        console.log(response);
        this.setState({ fiberColor: response.data });
      });
  }

  handleFiberCountChange(event) {
    this.setState({ fiberCount: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    this.generateFiberColor();
  }

  render() {
    return (
      <div className="min-h-screen bg-gray-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Fiber Tools
          </h2>
        </div>

        <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
          <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
            <form className="space-y-6" onSubmit={this.handleSubmit}>
              <div>
                <label htmlFor="fiberCount" className="block text-black text-sm font-medium">
                  Fiber Count
                </label>
                <div className="mt-1">
                  <input
                    id="fiberCount"
                    name="fiberCount"
                    type="number"
                    className="text-black shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                    value={this.state.fiberCount}
                    onChange={this.handleFiberCountChange}
                  />
                </div>
              </div>

              <div>
                <button
                  type="submit"
                  className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Generate Fiber Color
                </button>
              </div>

              <div className="mt-4">
                <p className="text-center text-gray-500">{this.state.fiberColor}</p>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default App;

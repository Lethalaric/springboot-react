'use strict';

import React from 'react';
import ReactDOM from 'react-dom';

const host = 'http://localhost:8080';

const fetchAllEmployees = () => {
    return fetch(
        host + '/custom/employee'
    ).then(response => response.json());
}

const addEmployee = (request) => {
    return fetch(
        host + '/custom/employee',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        }
    ).then(response => response.json())
}

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        fetchAllEmployees()
            .then(data => this.setState({employees: data}));
    }

    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        )
    }
}

class EmployeeList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            showAddEmployee: false,
            username: ""
        };
    }
    render() {
        const employees = this.props.employees.map(employee =>
            <Employee key={employee.username} employee={employee}/>
        );

        const onAddEmployeeClick = () => {
            this.setState({showAddEmployee: true});
        }

        const handleUsernameChange = (event) => {
            this.setState({...this.state, username: event.target.value});
        }

        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Role</th>
                        <th>Number Of Experiences</th>
                    </tr>
                    {employees}
                    </tbody>
                </table>
                <button onClick={onAddEmployeeClick}>Add new employee</button>
                {this.state.showAddEmployee && <AddEmployee />}
            </div>
        )
    }
}

class AddEmployee extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            fullName: "",
            role: "",
            numberOfExperience: 0,
            username: "",
        };
    }

    render() {
        const handleChange = (event) => {
            const target = event.target;
            const name = target.name;
            const value = target.value;

            this.setState({...this.state, [name]: value});
        }

        const handleSubmit = (event) => {
            addEmployee(this.state).then(r => console.log(r));
        }

        return (
            <div>
                <form onSubmit={handleSubmit}>
                    <div className={"form-grid"}>
                        <label>Username</label>
                        <input value={this.state.username} onChange={handleChange} name={"username"} />
                        <label>Full Name</label>
                        <input value={this.state.fullName} onChange={handleChange} name={"fullName"} />
                        <label>Role</label>
                        <input value={this.state.role} onChange={handleChange} name={"role"} />
                        <label>Number of experiences</label>
                        <input value={this.state.numberOfExperience} onChange={handleChange} name={"numberOfExperience"} />
                    </div>
                    <button type={"submit"}>Submit</button>
                </form>
            </div>
        )
    }
}

class Employee extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.employee.username}</td>
                <td>{this.props.employee.fullName}</td>
                <td>{this.props.employee.role}</td>
                <td>{this.props.employee.numberOfExperience} Years</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
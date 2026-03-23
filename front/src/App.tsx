import './App.css'
import Logo from './assets/logo-full.svg'
import ArrowRight from './assets/arrow-right.svg'

const API_URL = 'http://localhost:8080/accounts'

const fetchAccount = async (e: React.FormEvent<HTMLFormElement>) => {
  e.preventDefault();

  const body = {
    name: e.currentTarget.name,
    cpf: e.currentTarget.cpf,
    referalCode: e.currentTarget.referalCode
   }
   
  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(body)
    });
    if(response.ok) {
      console.log('Account created successfully');
    }
  } catch (err) {
    console.log('Error', err.message)
  }

}

function App() {
  return (
    <div id="login">
      <img src={Logo} alt="Cora" title="Cora" />

      <div className="account-form">
        <h2>Criar conta</h2>

        <div className="form-group">
          <label htmlFor="name">Nome:</label>
          <input
            type="text"
            id="name"
            required
            placeholder="Nome do titular da conta"
          />
        </div>

        <div className="form-group">
          <label htmlFor="name">CPF:</label>
          <input type="text" id="name" required placeholder="999.999.999-99" />
        </div>

        <div className="form-group">
          <label htmlFor="name">Código de indicação:</label>
          <input
            type="text"
            id="name"
            required
            placeholder="Digite o código de indicação"
          />
        </div>

        <div className="form-buttons">
          <button type="submit" onClick={fetchAccount}>
            Continuar
            <img src={ArrowRight} />
          </button>
        </div>
      </div>
    </div>
  )
}

export default App

/**
 * Function for retrieving a JSON list of items from backend server.
 * 
 * @returns Table row of data.
 */
function GetItems() {
  // React State Variable
  // itemList will store the list of items
  const [itemList, setItemList] = useState([]);
  
  // Fetch items from the backend server.
  const fetchItems = () => {
    axios.get("http://localhost:8080/api/items").then(response => {
      // Set the itemList state with the data retrieved
      setItemList(response.data);
    })
  }

  // Call the function above
  useEffect(() => {
    fetchItems();
  }, []);

  // Returns a table row for each item
  return itemList.map((item, index) => {
    return (
      <tr key={index}>
        <td>{item.id}</td>
        <td>{item.itemSku}</td>
        <td>{item.itemName}</td>
        <td>${item.itemCost}</td>
        <td>{item.complexityFactor}</td>
        <td>{item.complexityDescription}</td>
      </tr>
    )
  })
}




/**
 * Function for sending an item to the database.
 * 
 * @param {*} item Item to be sent to the database.
 */
function SendItem(item) {
  const sendPostRequest = async () => {
    try {
      await axios.post("http://localhost:8080/api/items/additem", item);
    } catch(error) {
      console.error(error);
    }
  }
  sendPostRequest();
}




/**
 * Displays a form to add an item to the database. 
 
 */
function AddItemForm(props) {
  const [itemSku, setItemSku] = useState('');
  const [itemName, setItemName] = useState('');
  const [itemCost, setItemCost] = useState(0);
  const [complexityFactor, setComplexityFactor] = useState(0);
  const [complexityDescription, setComplexityDescription] = useState('');

  // Messy but it works
  const handleSubmit = event => {
    event.preventDefault();
    const item = {
      "itemSku": itemSku,
      "itemName": itemName,
      "itemCost": itemCost,
      "complexityFactor": complexityFactor,
      "complexityDescription": complexityDescription
    }

    // Send item to our database
    SendItem(item);

    // Gotta fetch the new JSON list and sent it to our table
    const fetchItems = async () => {
      await axios.get("http://localhost:8080/api/items").then(response => {
        // Set the itemList state with the data retrieved
        props.func(response.data);
      })
    }

    const clearState = () => {
      setItemSku('');
      setItemName('');
      setItemCost(0);
      setComplexityFactor(0);
      setComplexityDescription('');
    }

    fetchItems();
    clearState();
  }

  return (
    <form onSubmit={handleSubmit} >
      <label>
        SKU
        <br />
        <input
          type="text"
          name="itemSku"
          value={itemSku}
          onChange={input => setItemSku(input.target.value)}
        />
      </label>
      <br /><br />
      <label>
        Name
        <br />
        <input
          type="text"
          name="itemName"
          value={itemName}
          onChange={input => setItemName(input.target.value)}
        />
      </label>
      <br /><br />
      <label>
        Cost
          <br />
        <input
          type="number"
          name="itemCost"
          value={itemCost}
          onChange={input => setItemCost(input.target.value)}
          step=".01"
        />
      </label>
      <br /><br />
      <label>
        ComplexityFactor
          <br />
        <input
          type="number"
          name="complexityFactor"
          value={complexityFactor}
          onChange={input => setComplexityFactor(input.target.value)}
        />
      </label>
      <br /><br />
      <label>
        Complexity Description
          <br />
        <input
          type="text"
          name="complexityDescription"
          value={complexityDescription}
          onChange={input => setComplexityDescription(input.target.value)}
        />
      </label>
      <br /><br />
      <button type="submit">Submit</button>
    </form>
  )
}

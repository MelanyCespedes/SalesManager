from flask import Flask, jsonify
import sqlite3

app = Flask(__name__)
DB_FILE = "clientes.db"

def get_db_connection():
    conn = sqlite3.connect(DB_FILE)
    conn.row_factory = sqlite3.Row  # Para devolver filas como diccionarios
    return conn

@app.route('/clientes', methods=['GET'])
def get_clientes():
    conn = get_db_connection()
    clientes = conn.execute('SELECT id, nombre FROM clientes').fetchall()
    conn.close()
    clientes_list = [dict(cliente) for cliente in clientes]
    return jsonify(clientes_list)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

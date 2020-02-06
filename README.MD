Для работы web-сервиса нужно:
1. На сервере баз данных MySQL создать базу (см. scripts/sql_scripts.sql)
2. Настроить подключение к созданной базе данных, изменив значения полей в классе C3p0DataSource:

    private static final String sqlUser = "***";
    
	private static final String sqlUserPassword = "***";
	
	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/task_webservice";
	
3. Запустить web-сервис.

Примеры запросов к web-сервису в файле scripts/requests.txt
create table if not exists answer(
    id integer primary key,
    answer_order int,
    question_id int,
    given_answer varchar(200),
    competitionresult_id int
);

create table if not exists question(
    id integer primary key,
    question varchar(200),
    correct_answer varchar(200),
    point_value int
);

create table if not exists competitionresult(
    id integer primary key,
    ip_address varchar(50),
    team_name varchar(200),
    team_code varchar(200),
    score int,
    start_submit_time date,
    end_submit_time date
);